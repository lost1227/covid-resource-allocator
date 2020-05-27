export class VolunteerTask {
  constructor(
    public id : number,
    public name : string,
    public location : string,
    public need : number,
    public description : string,
    public ownerId : number
  ) {}
}
