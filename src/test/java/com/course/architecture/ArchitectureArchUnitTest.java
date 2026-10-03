package com.course.architecture;

import com.course.architecture.overengineering.PragmaticUserService;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    @DisplayName("ArchUnit: Package-by-Feature slices must be free of cyclic dependencies")
    void packageByFeatureSlicesShouldBeFreeOfCycles() {
        ArchRule rule = slices().matching("com.course.architecture.packagebyfeature.(*)..")
                .should().beFreeOfCycles();

        rule.check(classes);
    }

    @Test
    @DisplayName("Verify PragmaticUserService achieves same result with zero accidental complexity")
    void testPragmaticUserService() {
        PragmaticUserService.UserRepository repo = id -> {
            if (id == 999L) return Optional.empty();
            return Optional.of(new PragmaticUserService.User(id, "Grace", "Hopper"));
        };
        PragmaticUserService service = new PragmaticUserService(repo);

        PragmaticUserService.UserDisplayNameDto result = service.getUserDisplayName(42L);
        assertThat(result.id()).isEqualTo(42L);
        assertThat(result.displayName()).isEqualTo("Grace Hopper");

        assertThatThrownBy(() -> service.getUserDisplayName(999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User not found");
    }
}
