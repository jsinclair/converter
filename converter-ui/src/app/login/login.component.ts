import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

import { User } from '../user';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // Displays login error messages
  error = null;

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit() {
  }

  // Attempt a login, only proceeds if both username and password are present
  login(username: string, password: string): void {
    console.log(username + " "
      + password);

    username = username.trim();
    password = password.trim();
    if (!username || !password) {
      this.error = "Both username and password are required.";
      return;
    }
    this.authService.attemptAuth({ username, password } as User)
      .subscribe(response => {
        if (!response.error) {
          this.error = null;
          this.router.navigate(['converter']);
        } else {
          this.error = response.error;
        }
        console.log(response);
      });
  }

}
