import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  departmentId = '';
  employees: any[] = [];
  selectedEmployee: any = null;

  constructor(private http: HttpClient) {}

  fetchEmployees() {
    this.http.get<any[]>(`http://localhost:8080/api/departments/${this.departmentId}/employees`)
      .subscribe(data => this.employees = data);
  }

  selectEmployee(emp: any) {
    this.selectedEmployee = emp;
  }

  deleteEmployee(emp: any, event: MouseEvent) {
    event.stopPropagation();
    this.http.delete(`http://localhost:8080/api/departments/${this.departmentId}/employees/${emp.id}`)
      .subscribe(() => this.fetchEmployees());
  }
}
