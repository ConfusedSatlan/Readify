package application.onlinebookstore.repository;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtil {
    private static final int ZERO_LENGTH = 0;

    public static <T> Specification<T> getSpecificationByParam(
            SpecificationProviderManager<T> specificationProviderManager,
            String[] items, String name) {
        Specification<T> spec = Specification.where(null);
        if (isParametersValid(items)) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider(name)
                    .getSpecification(items));
        }
        return spec;
    }

    private static boolean isParametersValid(String[] items) {
        return (items != null && items.length > ZERO_LENGTH);
    }
}
