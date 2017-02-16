/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Precio(id, precio, fechaInicioVigencia, fechaFinVigencia, indTablaPrecio, indTmpPrecio){
    this.id = id;
    this.precio = precio;
    this.fechaInicioVigencia = fechaInicioVigencia;
    this.fechaFinVigencia = fechaFinVigencia;
    this.indTablaPrecio = indTablaPrecio;
    this.indTmpPrecio = indTmpPrecio;
    this.indError = "";
    this.msjError = "";
}



