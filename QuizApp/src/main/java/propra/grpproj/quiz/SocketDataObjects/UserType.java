package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public enum UserType implements Serializable{
	DEFAULT,
	PUBOWNER,
	ADMIN,
	ADMINPUBOWNER,
	ERROR;
}
