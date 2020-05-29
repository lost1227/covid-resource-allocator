export enum SupplyType {
  REQUEST = "REQUEST",
  OFFER = "OFFER"
}
export class Supply {
  constructor(
    public id : number,
    public  name : string,
    public location : string,
    public need : number,
    public description : string,
    public ownerId : number,
    public type : SupplyType,
    public quantity : number,
    public photoId : number
  ) {}
}
