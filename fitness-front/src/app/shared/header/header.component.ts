import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {

  constructor() {}

  habilitarDesabilitarSidebar(): void {
    const sidebar = document.getElementById("sidebar");
    //const navbar = document.getElementById("navbar")
    //const main = document.getElementById("main");

    //sidebar.classList.toggle("hidden");
    sidebar.classList.toggle("hide");
    /*
    navbar.classList.toggle("bg-primaria");
    navbar.classList.toggle("bg-primaria/50");
    main.classList.toggle("bg-primaria/95");
    main.classList.toggle("bg-primaria/50");
    */
  }
}
