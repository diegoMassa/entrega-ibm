package com.ibm.bancoibm.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



public class Fechas
{

	public Fechas()
	{
	}

	static final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

	public static Date strToDate(String strFecha, String patron)
	{
		Date convertDate = null;
		try
		{
			convertDate = (new SimpleDateFormat(patron)).parse(strFecha);
			return convertDate;
		}
		catch(ParseException pex)
		{
			pex.printStackTrace();
		}
		return null;
	}

	public static String dateToStr(Date fecha, String patron){
		try{
			Format formatter = new SimpleDateFormat(patron);
			if (fecha != null) {
				return formatter.format(fecha);
			}
		}
		catch(Exception e)
		{
			System.out.println((new StringBuilder("Error en dateToStr (")).append(fecha).append(",").append(patron).append("): ").append(e.toString()).toString());
		}
		return "";
	}

	public static Date numberToDate(String fechaNumerica){
		Date convertDate = null;
		String fechaTotal =  "";
		String patron = "yyyy/MM/dd";

		try {
			if (fechaNumerica!=null && fechaNumerica.equals("")==false && Utilities.isNumeric(fechaNumerica) && fechaNumerica.trim().length()==8) {
				String ano = fechaNumerica.substring(0, 4);
				String mes = fechaNumerica.substring(4, 6);
				String dia = fechaNumerica.substring(6, 8);

				if (ano.equals("") || mes.equals("") || dia.equals("")) {
					return convertDate;
				}else {
					fechaTotal = ano+"/"+mes+"/"+dia;  		
					convertDate = (new SimpleDateFormat(patron)).parse(fechaTotal);
					return convertDate;
				}
			}else {
				System.out.println("Error en las validaciones de " + fechaNumerica);	
			}
		} catch (ParseException pex) {
			System.out.println("Error tratando de obtener una fecha de " + fechaNumerica);
		}
		return convertDate;
	}


	public static double diasEntreFechas(Date fechafinal, Date fechainicial)
	{
		double Ndias = 0.0D;
		try
		{
			Ndias = (fechafinal.getTime() - fechainicial.getTime()) / 0x5265c00L;
			return Ndias;
		}
		catch(Exception e)
		{
			return 0.0D;
		}
	}


	public static Date sumarConMinutos(Date fecha, int dias, int meses, int aF1os, int semanas, int minutos)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			c.add(Calendar.MINUTE, minutos);
			fecha = c.getTime();
			return fecha;
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new Date();
	}

	public static Date sumarConMinutosYHoras(Date fecha, int dias, int meses, int aF1os, int semanas, int horas, int minutos)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			c.add(Calendar.HOUR, horas);
			c.add(Calendar.MINUTE, minutos);
			fecha = c.getTime();
			return fecha;
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new Date();
	}


	public static Date sumar(Date fecha, int dias, int meses, int aF1os, int semanas)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			fecha = c.getTime();
			return fecha;
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new Date();
	}

	public static int getDiaDelMes(Date fecha)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(5);
	}

	public static int getDiaDeLaSemana(Date fecha)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(7);
	}

	public static int getSemanaDelAF1o(Date fecha)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(3);
	}

	public static Date sumar(Date fecha, int dias, int meses, int aF1os, int semanas, int diaDeLaSemanaQueQueda)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			c.set(7, diaDeLaSemanaQueQueda);
			fecha = c.getTime();
			System.out.println((new StringBuilder("Quedo en ")).append(dateToStr(fecha, "dd/MMMM/yyyy")).toString());
			return fecha;
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new Date();
	}

	public static Date moverHastaDiaDeLaSemana(Date fecha, int dia, boolean retrocediendo)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if(!retrocediendo && dia < c.get(7))
			{
				fecha = sumar(fecha, 0, 0, 0, 1);
				c.setTime(fecha);
			}
			c.set(7, dia);
			return c.getTime();
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de moverHastaDiaDeLaSemana una fecha");
		}
		return new Date();
	}

	public static Date moverHastaDiaDeLaSemana(Date fecha, int dia, boolean retrocediendo, int semanasStep)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if(!retrocediendo && dia < c.get(7))
			{
				fecha = sumar(fecha, 0, 0, 0, 1 * semanasStep);
				c.setTime(fecha);
			}
			c.set(7, dia);
			return c.getTime();
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de moverHastaDiaDeLaSemana una fecha");
		}
		return new Date();
	}

	public static Date setDayOfTheWeek(Date fecha, int dia)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.set(7, dia);
			return c.getTime();
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de setDayOfTheWeek");
		}
		return new Date();
	}

	public static Date moverHastaDiaDelMes(Date fecha, int dia, boolean retrocediendo)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if(!retrocediendo && dia < c.get(5))
			{
				fecha = sumar(fecha, 0, 1, 0, 0);
				c.setTime(fecha);
			}
			for(; !ExisteDiaEnMes(dia, c.get(2), c.get(1)); dia--);
			c.set(5, dia);
			return c.getTime();
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
		}
		return new Date();
	}

	public static boolean ExisteDiaEnMes(int dia, int mes, int aF1o)
	{
		if(mes == 1)
		{
			if(aF1o % 4 == 0)
				return dia >= 1 && dia <= 29;
				return dia >= 1 && dia <= 28;
		}
		if(mes == 0 || mes == 2 || mes == 4 || mes == 6 || mes == 7 || mes == 9 || mes == 11)
			return dia >= 1 && dia <= 31;
			return dia >= 1 && dia <= 30;
	}

	public static Date moverHastaPrimerDiaDelMes(Date fecha)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.set(5, 1);
			return c.getTime();
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
		}
		return new Date();
	}

	public static Date moverHastaDiaDelAF1o(Date fecha, int dia, int mes, int aF1o, boolean retrocediendo)
	{
		try
		{
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if(aF1o == -1)
				aF1o = c.get(1);
			if(!retrocediendo && dia < c.get(5))
			{
				fecha = sumar(fecha, 0, 1, 0, 0);
				c.setTime(fecha);
			}
			c.set(1, aF1o);
			c.set(2, mes);
			for(; !ExisteDiaEnMes(dia, c.get(2), c.get(1)); dia--);
			c.set(5, dia);
			return c.getTime();
		}
		catch(Exception e)
		{
			System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
		}
		return new Date();
	}

	public static XMLGregorianCalendar convertirDateAXMLGregorianDate(Date fecha) throws Exception{
		if (fecha == null) {
			return null;
		}
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		//    	gregorianCalendar.setTime(fecha != null ? fecha : strToDate("19000101", "yyyyMMdd"));    	
		gregorianCalendar.setTime(fecha);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		} catch (Exception e) {			
			throw e;
		}
	}

	public static Date sumarMesesAFecha(Date fecha, int numeroMeses){

		try {
			Calendar c = DateToCalendar(fecha);
			c.add(java.util.Calendar.MONTH, numeroMeses);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de sumar Meses a una fecha");
		}
		return null;
	}

	public static java.util.Calendar DateToCalendar(Date date){ 
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date restarDias(Date fch, int dias){
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fch.getTime());
		cal.add(Calendar.DATE, - dias);
		return new Date(cal.getTimeInMillis());
	}

	public static boolean verificarFechaEntreRangoDeFechas(Date fechaActual, Date fechaUltimoIngreso, Long diasMaximosPermitidos){

		boolean fechaPermitida = false;

		try {
			Date fechaMaximaPermitida = sumar(fechaUltimoIngreso, diasMaximosPermitidos.intValue(), 0, 0, 0);
			if (fechaActual.getTime() < fechaMaximaPermitida.getTime()) {
				fechaPermitida = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaPermitida;
	}

	public static Date trasnformDiaHoraSeparadoToDate(Date fechaSeleccionada, String horaConMinutos){
		Date fecha = null;
		String patronRetorna = "yyyy/MM/dd HH:mm:ss";
		String patronOnlyFechaSinHoras = "yyyy/MM/dd";
		try {
			String fechaRecortadaStringConcatenada = dateToStr(fechaSeleccionada, patronOnlyFechaSinHoras) + " " + horaConMinutos + ":00";
			fecha = strToDate(fechaRecortadaStringConcatenada, patronRetorna);
		} catch (Exception e) {
			System.out.println("Error tratando de obtener una fecha de " + fechaSeleccionada);
		}
		return fecha;
	}

	//Encuentra el dia de la semana segun la fecha actual -----Lunes,martes,miercoles...
	public static int getDayOfTheWeek(Date fecha){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		return cal.get(Calendar.DAY_OF_WEEK);  
	}



	//Si fecha inicial es menor a la fecha final --> devuelve true, de lo contrario devuelve false
	public static boolean compararHoras(String horaInicial, String horaFinal) {

		Date horaIni = null;
		Date horaFin = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			horaIni = dateFormat.parse(horaInicial);
			horaFin = dateFormat.parse(horaFinal);
			if (horaIni.compareTo(horaFin) < 0) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException ex) {
			System.out.println("Posee errores");
			return false;
		}
	}
	public static Date fechaActualSinHora(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
		Date fechaActual = new Date();  
		String fechaConFormato = sdf.format(fechaActual); 
		Date nuevaFecha;
		try {
			nuevaFecha = sdf.parse(fechaConFormato);
		} catch (ParseException e) {
			return null;
		}  
		return nuevaFecha;
	}

	public static Date actualDateWithTimeStamp(){
		return new Timestamp((new Date()).getTime());
	}

	public static java.sql.Date sqlDate(java.util.Date calendarDate) {
		return new java.sql.Date(calendarDate.getTime());
	}

	/**
	 * Método que calcula los meses que han pasado dese fecha inicio hasta
	 * fecha fin.
	 * @param fechaInicio: fecha de inicio de comparación.
	 * @param fechaFin: fecha de finalización de comparación.
	 * @return 0 si no ha pasado un mes o si se presenta alguna exepción.
	 * > 0 si han pasado almenos un mes.
	 */
	public static int calcularMesesAFecha(Date fechaInicio, Date fechaFin) {
		try {
			//Fecha inicio en objeto Calendar
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(fechaInicio);
			//Fecha finalización en objeto Calendar
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(fechaFin);
			//Cálculo de meses para las fechas de inicio y finalización
			int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
			int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
			//Diferencia en meses entre las dos fechas
			int diffMonth = endMes - startMes;
			//Si la el día de la fecha de finalización es menor que el día de la fecha inicio
			//se resta un mes, puesto que no estaria cumpliendo otro periodo.
			//Para esto ocupo el metoddo ponerAnioMesActual
			Date aFecha = ponerAnioMesActual(fechaInicio,fechaFin).getTime();
			if(formatearDate(fechaFin, "dd/MM/yyyy").compareTo(
					formatearDate(aFecha,"dd/MM/yyyy")) < 0){
				diffMonth = diffMonth - 1;
			}
			//Si la fecha de finalización es menor que la fecha de inicio, retorno que los meses
			// transcurridos entre las dos fechas es 0
			if(diffMonth < 0){
				diffMonth = 0;
			}
			return diffMonth;
		} catch (Exception e) {
			return 0;
		}
	}



	/**
	 * Método que formatea un afecha en base al formato pasado como
	 * parámetro.
	 * @param fecha: fecha a formatear
	 * @param pattern: formato que se dará a la fecha.
	 * @return Fecha formateada en base al formato de pattern.
	 * null si se presenta alguna excepción
	 */
	public static Date formatearDate(Date fecha, String pattern) {
		SimpleDateFormat formato = new SimpleDateFormat(pattern);
		Date fechaFormateada = null;
		try {
			fechaFormateada = formato.parse(formato.format(fecha));
			return fechaFormateada;
		} catch (Exception ex) {
			return fechaFormateada;
		}
	}    

	/**
	 * Método que remplaza el año y el mes de fecha y pone
	 * el año y mes de fechaActual
	 * @param fecha: fecha a remplazar el mes y el año
	 * @param fechaActual: fecha de la cual se tomara el mes y el año
	 * y se colocara en fecha
	 * @return Calendar con la nueva fecha calculada.
	 */
	public static Calendar ponerAnioMesActual(Date fecha, Date fechaActual) {
		try {
			Calendar cActual = Calendar.getInstance();
			cActual.setTime(fechaActual);
			Calendar cfecha = Calendar.getInstance();
			cfecha.setTime(fecha);
			//la fecha nueva
			Calendar c1 = Calendar.getInstance();
			c1.set(cActual.get(Calendar.YEAR), cActual.get(Calendar.MONTH), cfecha.get(Calendar.DATE));
			return c1;
		} catch (Exception e) {
			return null;
		}
	}

	public static Date moverHastaUnaHoraMinutoSegundo(Date fecha, int hora, int minuto, int segundo){
		try {

			Calendar c = Calendar.getInstance();
			c.setTime(fecha);

			c.set(Calendar.HOUR, hora);
			c.set(Calendar.MINUTE, minuto);
			c.set(Calendar.SECOND, segundo);

			fecha = c.getTime();

			return fecha;

		} catch (Exception e) {
			return null;
		}
	}

	public static Date moverHastaElUltimoDiaDelMes(Date fecha) throws Exception{
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		fecha = c.getTime();
		return fecha;
	}


	public static Long diferenciaEnDiasEntreDosFechas(Date fechaInicial, Date fechaFinal) {
		return (fechaFinal.getTime() - fechaInicial.getTime())/MILLSECS_PER_DAY;
	}
}