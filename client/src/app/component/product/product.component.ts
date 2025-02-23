import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../models/Product';
import { ProductService } from '../../service/product.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../service/user.service';
import { User } from '../../models/User';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-product',
  imports: [CommonModule, ReactiveFormsModule, MatSelectModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

  productFrom!:FormGroup;
  createdProduct!:Product;
  products!: Product[];
  updateStatusProduct!: Product;
  isProductCreated=false;
  users!:User[];
  
  constructor(private productSevice:ProductService,
    private userService:UserService,
    private router:Router,
    private fb:FormBuilder) { }

ngOnInit(): void {
this.getProduct();
this.productFrom = this.createProductForm();
this.getUsers();
}

createProductForm():FormGroup {
  return this.fb.group({
    category:['', Validators.compose([Validators.required])],
    courier: ['', Validators.compose([Validators.required])],
    senderId: [ '',Validators.compose([Validators.required])],
    receiverId: ['',Validators.compose([Validators.required])],
    name: ['',Validators.compose([Validators.required])]
  });
}

submit(): void {
  this.productSevice.createProduct({
    category: this.productFrom.value.category,
    courier: this.productFrom.value.courier,
    senderId: this.productFrom.value.senderId,
    receiverId: this.productFrom.value.receiverId,
    name:  this.productFrom.value.name
  }, ).subscribe( data => {
    this.createdProduct = data;
    console.log(data);  
    this.isProductCreated = true;
    window.location.reload();
});
}

getUsers():void {
  this.userService.getAllUsers()
   .subscribe(data => {
    this.users = data;
   });
}
getProduct():void {
  this.productSevice.getAll()
   .subscribe(data => {
    this.products = data;
   });
}

updateProduct(id: number):void {
  this.productSevice.updateProduct(id)
   .subscribe(data => {
    this.updateStatusProduct = data;
    window.location.reload();
   });
}
}
