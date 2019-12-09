//package propra.grpproj.quiz.services;
//
//import java.util.NoSuchElementException;
//
//import propra.grpproj.quiz.dataholders.ScoreboardEntity;
//import propra.grpproj.quiz.dataholders.User;
//
//public class UserService
//{
//
//    /**
//     * TODO create class UserRepository instead and synchronize both in a meta-logic class (GameLogic class?!?!)
//     */
//    private ScoreboardService scoreboardService;
//
//    public UserService(ScoreboardService scoreboardService)
//    {
//        super();
//        this.scoreboardService = scoreboardService;
//    }
//
//    public User register(String username, String password, String email)
//    {
//        User user = new User(findFreeId(), username, password, email);
//
//        // TODO better use userRepository.save(..)
//        ScoreboardEntity scoreboardEntity = scoreboardService.addUserToActualGame( user );
//
//        // return the saved instance and not the given parameter!
//        return new User( 	scoreboardEntity.getId(), 
//        					scoreboardEntity.getUsername(), 
//        					scoreboardEntity.getPassword, 
//        					scoreboardEntity.getEmail );
//    }
//
//    // ========================================================================
//    // helpers
//    // ========================================================================
//    
//    private long findFreeId()
//    {
//        // @formatter:off
//        // (1) find all users from database
//        
//        // (2) sort result list by id ascending
//        // (3) take greatest id / last entry in sorted list
//        //     => find greatest
//
//        // (4) + 1 to that id
//
//        return  1 +
//                scoreboardService
//                    .getScoreBoard()
//                    .stream()
//                    .mapToLong( scoreboardEntity ->{ return scoreboardEntity.getId(); })
//                    .max()
//                    .orElseThrow(NoSuchElementException::new);
//        // @formatter:on
//    }
//
//}
