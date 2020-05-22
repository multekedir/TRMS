import {Role} from './role';
import {Department} from './department';

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  role: Role;
  department: Department;
}
