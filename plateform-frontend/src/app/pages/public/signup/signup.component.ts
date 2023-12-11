import { Component, OnInit } from '@angular/core';
import {
  UntypedFormBuilder,
  FormControl,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/core/services/auth.service';
import { SignupRequestPayload } from './signup-request.payload';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  signupRequestPayload: SignupRequestPayload;
  signupForm: UntypedFormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService,
    private formBuilder: UntypedFormBuilder
  ) {}

  ngOnInit() {
    this.initSignUpForm();
  }
  initSignUpForm() {
    this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }
  signup() {
    if (this.signupForm.valid) {
      this.signupRequestPayload = this.signupForm.value;
      this.authService.signup(this.signupRequestPayload).subscribe(
        () => {
          this.router.navigate(['/login'], {
            queryParams: { registered: 'true' },
          });
        },
        () => {
          this.toastr.error('Registration Failed! Please try again');
        }
      );
    } else {
      this.toastr.error('Registration Failed! Please try again');
    }
  }
}
