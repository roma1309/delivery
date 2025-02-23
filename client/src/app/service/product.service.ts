import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewProduct } from '../models/NewProduct';
import { Observable } from 'rxjs';
import { Product } from '../models/Product';

const PRODUCT_API = 'http://localhost:8081/products';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  createProduct(product: NewProduct): Observable<Product>{
    return this.http.post<Product>(PRODUCT_API, product);
  }

  updateProduct(id: Number): Observable<Product>{
    return this.http.put<Product>(PRODUCT_API + "/update/" + id, null);
  }
  getAll(): Observable<Product[]>{
    return this.http.get<Product[]>(PRODUCT_API)
  }
}
