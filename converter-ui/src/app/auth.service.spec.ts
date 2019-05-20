import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';
import { User } from './user';

describe('AuthService', () => {
  beforeEach(() => TestBed.configureTestingModule({

  }));

  it('should log in', () => {
    let service = new AuthService();
    const user = { string: 'user', string: 'pass' } as User;

    service.attemptAuth(user)
      .subscribe(response => {
        expect(response.error).toMatch('');
      });
  });
});
