package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public enum UserType implements Serializable{
	DEFAULT,
	PUB_OWNER,
	ADMIN,
	ADMIN_PUBOWNER,
	ERROR;
}
