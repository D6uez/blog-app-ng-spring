import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {

  pageTitle = "Administrator";

  constructor() { }

  ngOnInit(): void {
    if (!window.location.hash) {
      window.location.hash = window.location + '#loaded';
      window.location.reload();
    }
  }

}
