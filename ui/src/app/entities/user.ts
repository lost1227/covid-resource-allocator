export class User {
  constructor(
    public id : number,
    public name : string,
    public location : string,
    public userType : string,
    public description : string,
    public skillset : string[],
    public photoId : number
  ) {}
}
