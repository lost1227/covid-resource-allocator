import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


export class ApiService {

    verifyResponse<T extends ResponseModel>(response : Observable<T>) : Observable<T> {
        return response.pipe(map(model => {
            if(!model.ok) {
                throw new Error("Server response is not ok");
            } else {
                return model;
            }
        }))
    }
    
}
export interface ResponseModel {
    ok : boolean
}
export class AuthenticatedRequestModel {
    constructor(
        public authToken : string
    ) {}
}
