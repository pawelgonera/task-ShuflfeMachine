import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ShuffleMachine
{
    private final static Pattern FIELD_PATTERN = Pattern.compile("^\"(.+)\"$");
    private String filename;
    private int amountOfWinners;

    private List<Participant> participantList = new LinkedList<>();
    private List<Participant> winners = new LinkedList<>();
    private List<String> emailList = new LinkedList<>();


    public ShuffleMachine(String filename, int amountOfWinners)
    {
        this.filename = filename;
        this.amountOfWinners = amountOfWinners;
    }

    public ShuffleMachine(String filename) {
        this.filename = filename;
    }

    public List<Participant> getWinners(){
        participantList = getListOfParticipants();
        emailList = getMails();

        for(int i = 0; i < amountOfWinners; i++){

            if(!participantList.isEmpty()) {
                Optional<Participant> winner = getWinner(participantList, emailList);

                if (winner.isPresent()) {
                    winners.add(winner.get());

                    boolean isWinnerIsRecommender = participantList.stream()
                            .anyMatch(participant -> participant.getRecommender().equals(winner.get().getName()));

                    if (isWinnerIsRecommender && !winner.get().getWasDrawn()) {
                        winner.get().setWasDrawn(true);
                    } else {
                        participantList.remove(winner.get());
                        emailList.remove(winner.get().getEmail());
                    }
                }
            }
        }

        return winners;
    }

    public List<Participant> getListOfParticipants()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {

            String[] fields;
            String readLine;

            int i = 0;
            while((readLine = reader.readLine()) != null)
            {
                fields =  readLine.split(",");
                if(i == 0){
                    i++;
                    continue;
                }

                Participant participant = new Participant();
                participant.setId(i);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd H:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(parseField(fields[0]).substring(0, 18), formatter);
                participant.setDate(localDateTime);
                participant.setName(parseField(fields[1]));
                participant.setEmail(parseField(fields[2]));
                participant.setSuggestion(parseField(fields[3]));
                participant.setRecommender(parseField(fields[4]));


                participantList.add(participant);
                i++;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return participantList;
    }

    public int getAmountOfWinners(){
        return getListOfParticipants().size();
    }

    private List<String> getMails()
    {
        for (Participant participant : participantList) {
            String element = parseField(participant.getEmail());
            if (Objects.requireNonNull(element).contains("@")) {
                emailList.add(element);
            }
        }

        return emailList;
    }

    private Optional<Participant> getWinner(List<Participant> participantList, List<String> emailList){
        Collections.shuffle(emailList);
        String drawnEmail = emailList.get(0);

        return participantList.stream()
                              .filter(participant -> participant.getEmail().equals(drawnEmail))
                              .findFirst();
    }


    private static String parseField(String field)
    {
        Matcher fieldMatcher = FIELD_PATTERN.matcher(Objects.requireNonNull(field));

        if (fieldMatcher.matches()) {
            return fieldMatcher.group(1);
        }

        return field;
    }
}
