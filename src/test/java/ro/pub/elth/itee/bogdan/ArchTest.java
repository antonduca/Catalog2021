package ro.pub.elth.itee.bogdan;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ro.pub.elth.itee.bogdan");

        noClasses()
            .that()
                .resideInAnyPackage("ro.pub.elth.itee.bogdan.service..")
            .or()
                .resideInAnyPackage("ro.pub.elth.itee.bogdan.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..ro.pub.elth.itee.bogdan.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
