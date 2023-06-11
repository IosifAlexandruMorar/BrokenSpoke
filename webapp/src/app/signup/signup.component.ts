import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UserService } from "../services/user.service";

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

  constructor(private formBuilder: FormBuilder, private userService: UserService) { }

  ngOnInit() { }

  onSubmit():void {
    console.log(this.signupForm.value);
    if (this.signupForm.invalid) {
      return;
    }

    const formData = this.signupForm.value;
    this.userService.signUpUser(formData).subscribe(
      response => {
        console.log('Sign up successful:', response);
        this.signupForm.reset();
      },
      error => {
        console.error('Sign-up failed: ', error);
      }
    );
  }
}
