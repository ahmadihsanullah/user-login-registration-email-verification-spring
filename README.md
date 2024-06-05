# user authentication 


Proyek membuat user authentication berupa:
 -  Registration
 -  Login
 - Verifikasi akun lewat email

![img.png](img/img.png)


# Langkah - langkah

## user app
1. Buat entity user : @Entity
2. Buat implementasi UserDetails: @AllArgsConstructor
3. Buat repository untuk ambil email user :@Repository
4. Buat implementasi UserDetailsService : @Service
    - Pake poin no 3 untuk loadByUsername

## registration
1. Buat user controller untuk registrasi
   - Kasih parameter userrequest
   - balikan berupa registration service 

## security
1. Buat fungsi return SecurityFilterChain dengan parameter httpSecurity
    - atur csrf dan request user