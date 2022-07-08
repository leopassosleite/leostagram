import { Component, OnInit } from '@angular/core';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public login: HomeService) { }

  ngOnInit(): void {
  }

  public logout() {
    this.login.logout();
    window.location.reload();
  }

}
