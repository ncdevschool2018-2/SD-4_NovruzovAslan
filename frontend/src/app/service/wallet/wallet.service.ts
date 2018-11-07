import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Wallet } from "../../model/wallet";

@Injectable({
  providedIn: "root"
})
export class WalletService {
  constructor(private http: HttpClient) {}

  // Ajax request for wallet data
  getWallets(): Observable<Wallet[]> {
    return this.http.get<Wallet[]>("http://localhost:8081/api/wallets");
  }

  saveWallet(wallet: Wallet): Observable<Wallet> {
    return this.http.post<Wallet>("http://localhost:8081/api/wallets", wallet);
  }

  deleteWallet(walletId: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/api/wallets/" + walletId);
  }
}
