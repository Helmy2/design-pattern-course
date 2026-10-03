package com.course.architecture;

import com.course.architecture.overengineering.OverEngineeredUserService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;

class ArchitectureArchUnitTest {

    private final JavaClasses classes = new ClassFileImporter()
            .importPackages("com.course.architecture");

    @Test
    @DisplayName("ArchUnit: Controllers must never access Repositories directly")
    void controllersShouldNotAccessRepositories() {
        ArchRule rule = noClasses().that().resideInAPackage("..controllers..")
                .should().dependOnClassesThat().resideInAPackage("..repositories..");

        rule.check(classes);
    }

    @Test
    @DisplayName("Verify OverEngineeredUserService behavior in starter")
    void testOverEngineeredUserService() {
        OverEngineeredUserService.UserRepository repo = id -> Optional.of(
                new OverEngineeredUserService.User(id, "Ada", "Lovelace")
        );
        OverEngineeredUserService.NameFormattingStrategy strategy = new OverEngineeredUserService.DefaultNameFormattingStrategy();
        OverEngineeredUserService.IUserService service = new OverEngineeredUserService.UserServiceImpl(repo, strategy);

        OverEngineeredUserService.UserDisplayNameDto result = service.getUserDisplayName(
                new OverEngineeredUserService.UserIdSpecification(1L)
        );

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.displayName()).isEqualTo("Ada Lovelace");
    }
}
