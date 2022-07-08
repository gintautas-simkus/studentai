package gintautassimkus.studentai;

class UserAlreadyExistsException extends Exception {
	UserAlreadyExistsException(String msg) {
		super(msg);
	}
}