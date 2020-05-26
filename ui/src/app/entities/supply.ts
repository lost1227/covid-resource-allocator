export enum SupplyType {
    REQUEST = "REQUEST",
    OFFER = "OFFER"
  }
export class Supply {
    constructor(
        public readonly id : number,
        public readonly name : string,
        public readonly location : string,
        public readonly need : number,
        public readonly description : string,
        public readonly ownerId : number,
        public readonly type : SupplyType,
        public readonly quantity : number
    ) {}
}
