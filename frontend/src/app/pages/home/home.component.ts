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
    email: '',
    password: ''
  }

  constructor(private snack: MatSnackBar, private login: HomeService) { }

  ngOnInit(): void {
  }

  formSubmit() {
    console.log("Login botão");

    if (
      this.loginData.email.trim() == '' ||
      this.loginData.email == null
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
      (data:any)=> {
        console.log('succsess');
        console.log(data);
      },
      (error) => {
        console.log('Error !');
        console.log(error);
      }
    )
  }
}