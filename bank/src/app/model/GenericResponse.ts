import { Page } from "./page";

export class GenericResponse {

  public page:Page;
  
  constructor(
    public codigo: string,
    public mensaje: string,
    public lista: Array<any>
  ) {}

}
