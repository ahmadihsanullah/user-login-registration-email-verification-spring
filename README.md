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
    - hasildari loadByusername user
    - lalu return new AppUserDetail di no 2, masukin parameter user kedalamnya

## registration
1. Buat user controller untuk registrasi
   - Kasih parameter userrequest
   - balikan berupa registration service 

## security
1. Buat fungsi return SecurityFilterChain dengan parameter httpSecurity
    - atur csrf dan request user

## registration service
1. buat email validator 'regex'
2. didalam appuseerservice kita buat fungsi baru untuk menangani register user  
  - yaitu signUp(User user)
  - cek apakah email sudah digunakan oleh user lain
  - jika ya throw exception
  - encode password user menggunakan BCryptPasswordEncoder (dependency injection)
  - set user.password dengan hasil encode
  - save user 
3. didalam registartion service panggil singUp dan isi parameternya dari input user