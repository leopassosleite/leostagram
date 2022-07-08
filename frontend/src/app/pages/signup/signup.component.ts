import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService) { }

  public user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  };

  ngOnInit(): void { }

  formSubmit() {
    console.log(this.user)
    if (this.user.username == '' || this.user.username == null) {
      alert('Para efetuar seu cadastro, preencha os campos em branco')
      return;
    }
    //addUser: userService
    this.userService.addUser(this.user).subscribe(
      (data) => {
        console.log(data);
        alert('Usuário cadastrado');
        window.location.href = '';
      },
      (error) => {
        console.log(error);
        alert('Algo deu errado');
      }
    );
  }
}
