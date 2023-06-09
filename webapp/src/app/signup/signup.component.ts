import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UserService } from "../services/user.service";
import {SignupDto} from "../_model/signupDto";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm = this.formBuilder.group({
    userName: ['', Validators.required],
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });
  showNotification: boolean = false
  formData: SignupDto ={
    userName:"",
    firstName:"",
    lastName:"",
    email:"",
    password:"",
    hireDate:"",
    idRole:0
  }

  constructor(private formBuilder: FormBuilder, private userService: UserService) { }

  ngOnInit() { }

  onSubmit():void {
    console.log("signupForm:");
    console.log(this.signupForm.value);
    if (this.signupForm.invalid) {
      return;
    }

    this.formData.firstName = this.signupForm.value.firstName;
    this.formData.lastName = this.signupForm.value.lastName;
    this.formData.email = this.signupForm.value.email;
    this.formData.password = this.signupForm.value.password;
    this.formData.userName = this.signupForm.value.userName;

    this.userService.signUpUser(this.formData).subscribe(
      response => {
        console.log('Sign up successful:', response);
        this.showNotification = true;
        this.signupForm.reset();
      },
      error => {
        console.error('Sign-up failed: ', error);
      }
    );
  }

  closeNotification() {
    this.showNotification = false;
  }
}
