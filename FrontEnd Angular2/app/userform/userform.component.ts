import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';

import { Observable } from '../../../node_modules/rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-userform',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css']
})
export class UserformComponent implements OnInit {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  employees: Employee[] = [];
  temp: Employee = new Employee;

  newPost: Observable<any>;


  readonly ROOT_URL = "http://localhost:8086/api/multipleEmployees";
  posts: any;
  constructor(private http: HttpClient) { }

  getPosts() {
    this.posts = this.http.get(this.ROOT_URL)
  }
  createPost(emp: Employee[]): void {
    this.createPost2(emp).subscribe();
  }
  createPost2(emp: Employee[]): Observable<Employee[]> {
    this.employees=[];
    return this.http.post<Employee[]>(this.ROOT_URL, emp, this.httpOptions);
  }

  addEmployee() {
    var adder: Employee = new Employee;
    adder.name = this.temp.name;
    adder.salary = this.temp.salary;
    adder.department = this.temp.department;
    this.employees.push(adder);
  }

  ngOnInit() {
  }

}