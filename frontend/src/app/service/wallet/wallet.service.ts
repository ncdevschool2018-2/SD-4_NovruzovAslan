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
    return this.http.get<Wallet[]>("/api/wallets/all");
  }

  getWalletsByUserId(userId: string): Observable<Wallet[]> {
    return this.http.get<Wallet[]>("/api/wallets/all?user_id=" + userId);
  }

  getWalletsPageByUserId(page: number, size: string, userId?: string): Observable<Wallet[]> {
    if(!userId)
      return this.http.get<Wallet[]>("/api/wallets?page="+page+"&size="+size);
    return this.http.get<Wallet[]>("/api/wallets?page="+page+"&size="+size+"&user_id=" + userId);
  }

  getTotalPages(size: string, user_id?: string): Observable<number> {
    if(!user_id)
      return this.http.get<number>("/api/wallets/total-pages?size="+size);
    return this.http.get<number>("/api/wallets/total-pages?size="+size+"&user_id="+user_id);
  }

  transaction(from: string, to: string, value: number): Observable<number> {
    return this.http.post<number>("api/wallets/transaction",
      { from, to, value });
  }

  saveWallet(wallet: Wallet): Observable<Wallet> {
    return this.http.post<Wallet>("/api/wallets", wallet);
  }

  deleteWallet(walletId: string): Observable<void> {
    return this.http.delete<void>("/api/wallets/" + walletId);
  }
}
