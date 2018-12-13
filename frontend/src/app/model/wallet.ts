import {User} from "./user";

export class Wallet {
  id: string;
  user: User;
  name: string;
  description: string;
  value: number;
  valute: string; // need char

  static cloneBase(wallet: Wallet): Wallet {
    let clonedWallet: Wallet = new Wallet();
    clonedWallet.id = wallet.id;
    // clonedWallet.user = wallet.user;
    clonedWallet.name = wallet.name;
    clonedWallet.user = User.cloneBase(wallet.user);
    clonedWallet.description = wallet.description;
    clonedWallet.value = wallet.value;
    clonedWallet.valute = wallet.valute;
    return clonedWallet;
  }
}
