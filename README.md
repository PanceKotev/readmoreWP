Панче Котев - 175016, семинарска по предмет Веб Програмирање, ФИНКИ.

Readmore - Веб апликација правена со Spring Boot, postgreSQL и ReactJs како главни технологии, апликација за разгледување на книги и нивно лајкнување/review со автентикација.

Додатни технологии користени:
Backend:
Spring Security,
JWT Tokens
PostgreSQL

Frontend:
react-toastify,
bootstrap,
react-rating,
jwttoken,

Инструкции за стартување:
Пошто не може да се прати датабазата, треба да се креира postgreSQL датабаза и да се поврзи со springboot проектот во application.properties. Се иницијализираат неколку книги и автори и др со стартувањето на апликацијата од intellij, но неможев да иницијализирам user заради тоа што се енкриптирани податоците, мора да се регистрираат со линкот од react апликацијата. Неможев да довршам со кодот за front-end за креирање на review едит и бришење. Може да се креира review преку postman или нешто друго после sign_in со внесување на авторизација header на api /api/review/create со JWT кодот што се добива со /api/auth/signin, исто така и за /api/review/edit. Без авторизација не може да се пристапува до user profile, да се лајкнува и да се гледат деталите и review за една книга во front-end. React апликацијата се стартува со cmd од frontend фолдерот со npm start.

https://imgur.com/EJeYAKH
https://imgur.com/N0kbjfT
https://imgur.com/ys8db1H

