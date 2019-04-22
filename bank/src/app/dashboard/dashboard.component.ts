import { Component, OnInit } from '@angular/core';

import { UsuarioService } from '../usuario.service';
import { Router, ActivatedRoute } from '@angular/router';

/*
- feather-icons is a directory installed in node_modules.
- I dont have to specify the whole path like '../node_modules/path/to/feather-icons'.
- Also rememeber to call the feather.replace() inside ngOnInit
- because it needs to first make sure the component loads first
- Source: https://stackoverflow.com/questions/44995317/how-to-use-svg-icon-set-package-from-node-modules-in-angular4?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
*/
import * as feather from 'feather-icons';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user: string;
  constructor(private router: Router,
    private usuarioService: UsuarioService) {
    if(usuarioService.userInSession == null){
      this.router.navigate(['/']);
    }else{
      this.user = 'Bienvenido, ' + usuarioService.userInSession;
    }
  }

  ngOnInit() {
    feather.replace();
  }

}
