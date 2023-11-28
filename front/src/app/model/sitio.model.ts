export class Sitio{
  constructor(
    public nombre: string,
    public tipo: string, // hotel, hospedaje, residencia, apto privado, habitación en casa compartida
    public horarioCheckIn: string,
    public horarioCheckOut: string,
    public ubicacion: {
      departamento: string,
      ciudadMunicipio: string
    },
    public cantidadHabitaciones?: number,
    ) {}
}
