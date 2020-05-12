import React, { Component } from 'react';
import {BrowserRouter as Router, Route, Redirect} from 'react-router-dom'
import Auth from "./service/authService"
import {ACCESS_TOKEN} from "./constants/index"
import {toast, ToastContainer, ToastPosition} from 'react-toastify'
import Header from "./components/Header/Header"
import bg2 from "./Images/bg2.jpeg"
import Login from "./components/User/Login/Login"
import Books from "./components/Book/Books/Books"
import Register from "./components/User/Register/Register"
import Profile from "./components/User/Profile/Profile"
import PrivateRoute from "./components/common/PrivateRoute/PrivateRoute"
import LoadingIndicator from "./components/common/LoadingIndicator/LoadingIndicator"
import GS from "./service/genreService"
import BookDetails from './components/Book/BookDetails/BookDetails';
import BookAdd from './components/Book/BookAdd/BookAdd';

class App extends Component {
  
  constructor(props){
    toast.configure({position:ToastPosition.TOP_CENTER,autoClose:3000});
    super(props);
    this.state={
      currentUser: null,
      isAuthenticated: false,
      isLoading: false,
      genres:[]
    };
    this.handleLogout = this.handleLogout.bind(this);
    this.loadCurrentUser = this.loadCurrentUser.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
    this.loadGenreNames=this.loadGenreNames.bind(this);
  }
  loadCurrentUser = () => {
    this.setState({
      isLoading: true
    });
    Auth.getCurrentUser()
        .then(response => {
            this.setState({
                currentUser: response.data,
                isAuthenticated: true,
                isLoading:false
            });
        }).catch(error => {
          this.setState({
            isLoading: false
          });
    });
  };
  handleLogin = (request) => {
    toast.success("You're successfully logged in.")
    this.loadCurrentUser();
  };
  handleLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    toast.success("You've successfully logged out.")
    this.setState({
        currentUser: null,
        isAuthenticated: false
    });
  };
  loadGenreNames=()=>{
      GS.getAll().then((data)=>{
        this.setState({
          genres:data.data
        });
      }).catch(error=>{
          console.log(error);
      });
  }
  componentDidMount(){
    this.loadGenreNames();
    this.loadCurrentUser();

   
  }
  render() {
    if(this.state.isLoading){
      return <LoadingIndicator/>
    }
    let logiranje=[];
    if(!this.state.isAuthenticated)
      logiranje=<Login onLogin={this.handleLogin} getUser={this.loadCurrentUser}/>
    else
      logiranje=<Redirect to="/"/>
    return (
      <Router>
        <div className="App" style={  {minHeight:'100vh', width:'100%',padding:'0',background: "url("+bg2+")",
          backgroundPosition: 'center center',backgroundSize: 'cover',backgroundAttachment: 'fixed'}}>
          <Header genreNames={this.state.genres} isAuthenticated={this.state.isAuthenticated} currentUser={this.state.currentUser} onLogout={this.handleLogout}/>
          <div className="container">
            <Route path={"/signin"} exact>
              {logiranje}
            </Route>
            <Route path={"/signup"} exact>
              <Register></Register>
            </Route>
            <Route path={"/"} exact ><Books authenticated={this.state.isAuthenticated} listTypeBy=""/>
              </Route>
            
            <Route path={"/genre/:genreName"} user={this.state.currentUser} render={(props)=><Books authenticated={this.state.isAuthenticated} listTypeBy="genre" {...props} />} />
            <Route path={"/author/:authorName"} render={(props)=><Books authenticated={this.state.isAuthenticated} listTypeBy="author" {...props} />} />
            <Route path={"/series/:seriesName"} render={(props)=><Books authenticated={this.state.isAuthenticated} listTypeBy="series" {...props} />} />
            <Route path={"/search/:searchWord"} render={(props)=><Books authenticated={this.state.isAuthenticated} listTypeBy="search" {...props} />} />
            <PrivateRoute path={"/books/create"} authenticated ={this.state.isAuthenticated} component={BookAdd} exact ></PrivateRoute>
            <PrivateRoute exact path="/book/:bookName" user={this.state.currentUser} authenticated={this.state.isAuthenticated} component={BookDetails}/>
            <PrivateRoute exact path="/users/:username" jwtRefresh={this.handleLogout} authenticated={this.state.isAuthenticated} component={Profile}/>
          </div>
        </div>
        </Router>
    );
  }
}

export default App;