package watne.csvwritertest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import watne.csvwritertest.model.BasicExternalUserVO;
import watne.csvwritertest.model.ExternalUserVO;
import watne.csvwritertest.model.ExternalUserWithContactInfoVO;
import watne.csvwritertest.model.user;
import watne.csvwritertest.service.CSVService;
import watne.csvwritertest.service.UserGeneratingService;

@Component
public class CsvOutputTester implements CommandLineRunner {
    private final CSVService csvService;

    public CsvOutputTester(final CSVService csvService) {
        this.csvService = csvService;
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Testing getting CSV output for a class and subclass");
        final List<user> userList = UserGeneratingService.getUserList();
        System.out.println("userList: " + userList);

        if (!CollectionUtils.isEmpty(userList)) {
            // Get Lists of BasicExternalUserVOs and ExternalUserWithContactInfoVOs.
            final List<BasicExternalUserVO> listBasic = new ArrayList<>();
            final List<ExternalUserWithContactInfoVO> listWithContacts = new ArrayList<>();

            userList.forEach(x -> {
                listBasic.add(new BasicExternalUserVO(x.email()));
                listWithContacts.add(new ExternalUserWithContactInfoVO(x.email(), x.name()));
            });

            System.out.println("listBasic: " + listBasic);
            showCsvOutput(listBasic);
            System.out.println("listWithContacts: " + listWithContacts);
            showCsvOutput(listWithContacts);
        }
    }

    private void showCsvOutput(final List<? extends ExternalUserVO> userList) {
        System.out.println("CSV format:");

        if (!CollectionUtils.isEmpty(userList)) {
            final ExternalUserVO externalUserVO = userList.get(0);

            if (externalUserVO instanceof ExternalUserWithContactInfoVO) {
                final List<ExternalUserWithContactInfoVO> usersWithInfo = new ArrayList<>();
                userList.forEach(x -> usersWithInfo.add((ExternalUserWithContactInfoVO) x));
                printInfo(usersWithInfo, ExternalUserWithContactInfoVO.class);
            } else if (externalUserVO instanceof BasicExternalUserVO) {
                final List<BasicExternalUserVO> basicUsers = new ArrayList<>();
                userList.forEach(x -> basicUsers.add((BasicExternalUserVO) x));
                printInfo(basicUsers, BasicExternalUserVO.class);
            } else {
                System.out.println("*** UNKNOWN TYPE IN LIST: " + externalUserVO.getClass().getName());
            }
        } else {
            System.out.println("*** EMPTY LIST!!! ***");
        }
    }

    private <T> void printInfo(final List<T> usersWithInfo,
            final Class<T> listClass) {
        try {
            System.out.println(
                    csvService.getCsvForItemsInList(usersWithInfo, listClass));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
