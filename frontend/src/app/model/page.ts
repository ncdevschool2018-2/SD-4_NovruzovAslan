export class Page<T> {
  content: T[] = [];
  last: boolean;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  totalElements: number;
}
