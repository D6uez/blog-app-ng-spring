import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlogDetailComponent } from './blogs/blog-detail.component';
import { BlogListComponent } from './blogs/blog-list.component';
import { CreateBlogComponent } from './blogs/create-blog.component';
import { EditBlogComponent } from './blogs/edit-blog.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: "blogs/editBlog/:id", component: EditBlogComponent },
  { path: "blogs/newBlog", component: CreateBlogComponent },
  { path: "blogs/:id", component: BlogDetailComponent },
  { path: 'blogs', component: BlogListComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: "home", component: HomeComponent },
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "**", redirectTo: "welcome", pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
