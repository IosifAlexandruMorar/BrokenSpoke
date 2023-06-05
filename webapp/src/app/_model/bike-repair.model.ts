import {EmployeeModel} from "./employee.model";

export interface BikeRepair {
  repairId?: number;
  description: string;
  startDate: string;
  dueDate: string;
  clientName: string;
  clientPhone: number;
  status: string;
  price: number;
  employeeId: number;

  employee: EmployeeModel;
}
