package propra.grpproj.quiz.services;

import java.util.Optional;

import propra.grpproj.quiz.dataholders.User;
import propra.grpproj.quiz.repositories.sqlite.UserRepository;


/**
 * 
 * @author Daniel
 *
 */
public class UserService
{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        super();
        this.userRepository = userRepository;
    }
    
    public void createNewUser(String username,String email,String password, propra.grpproj.quiz.SocketDataObjects.UserType usertype)
    {
        if(userRepository.findByEmail(email).isPresent())
            throw new RuntimeException("A user with email=["+email+"] already exists");
        
        userRepository.save( new User(nextFreeId(), username, password, email, usertype.toString()) );
    }

    public void deleteUser(String username, String password)
    {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent())
        {
            User existingUser = user.get();
            userRepository.delete(existingUser);
        }
    }

    /**
     * <p>
     * This method authenticates a user by its email address and the given password.
     * If no user found by that email, an exception is thrown, otherwise the method
     * returns true in case of a matching password and false if the password does
     * not match to that user.
     * 
     * @param email
     * @param plainPassword the plain password. In other words: not the encrypted
     * one!
     * @return true if the email and password combination matches, false otherwise
     * @throws RuntimeException if there is no such user by email
     */
    public boolean authenticate(String email, String plainPassword)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
        {
            return user.get().getPassword().equals(plainPassword);
        } else
        {
            throw new RuntimeException("No user found by email");
        }
    }
    
    // ========================================================================
    // helper methods
    // ========================================================================

    /**
     * Returns the next incremental free ID.
     * 
     * @return the next incremental free ID.
     */
    private Long nextFreeId()
    {
        Long probe = 1L;

        while (userRepository.existsById(probe))
        {
            probe = probe + 1;
        }

        return probe;
    }


}
