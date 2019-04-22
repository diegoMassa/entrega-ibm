import { Tarjetas } from "./tarjetas";

export class Consumos {

    //Datos del consumo
    tranId : Number;
    fecha : Date;
    fechaCadena : String;
    monto : Number;
    descripcion : String;
    
    //Datos de la tarjeta
    tarjeta : Tarjetas;
    tarjeId_Tarjetas : Number;
    numeroTarjeta : Number;
    ccv : Number;
    tipoTarjeta : String;

    //Datos del cliente
    idCliente : Number;
    nombreCliente : String;
    
    constructor(){
    }
}