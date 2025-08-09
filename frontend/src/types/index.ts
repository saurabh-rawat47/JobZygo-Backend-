export interface User {
  id?: string;
  username: string;
  email: string;
  password?: string;
  userType?: string;
}

export interface JobPost {
  id?: string;
  profile: string;
  exp: number;
  jobType: string;
  companyName: string;
  desc: string;
  salary: number;
  location: string;
  techs: string[];
}

export interface AuthResponse {
  success: boolean;
  message: string;
  user?: User;
}

export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data?: T;
}
