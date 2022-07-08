import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public loginData = {
    username: '',
    password: ''
  }

  constructor(private snack: MatSnackBar, private login: HomeService) { }

  ngOnInit(): void {
  }

  formSubmit() {
    console.log("Login botão");

    if (
      this.loginData.username.trim() == '' ||
      this.loginData.username == null
    ) {
      this.snack.open('Informe seu Email !! ', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.loginData.password.trim() == '' ||
      this.loginData.password == null
    ) {
      this.snack.open('Informe sua Senha !! ', '', {
        duration: 3000,
      });
      return;
    }

    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log('succsess');
        console.log(data);

        //Login
        this.login.loginUser(data.token);
        this.login.getCurrentUser().subscribe(
          (user: any) => {
            this.login.SetUser(user);
            console.log(user);

            //redireciona admin-dashboard e user-dashboard
            if (this.login.getUserRole() == "ADMIN") {
              window.location.href = '/admin';
            } else if (this.login.getUserRole() == 'NORMAL') {
              window.location.href = '/user-dashboard';
            } else {
              this.login.logout();
            }
          });
      },
      (error) => {
        console.log('Error !');
        console.log(error);
        this.snack.open("Seu Username ou Senha foi informado incorretamente !!", '', {
          duration: 3000,
        });
      }
    )
  }
}