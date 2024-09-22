package com.example.libraryproject.constant;

public final class SecurityPathConstants {
    public static final String[] TEST_PERMIT_ALL = {
            "/api/v1/**",
    };
    public static final String[] PERMIT_ALL_LIST = {
            "/test/testNoAuth",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/auth/register-user",
            "/api/v1/auth/login",
            "/api/v1/auth/refresh-token/**",
            "/api/v1/book-management/search-books/**",
            "/api/v1/book-management/filter-books/**",
            "/api/v1/report/get-most-read-books/**",
            "/api/v1/book/get-all-books/**",
            "/api/v1/book/get-book-by-id/**",
            "/api/v1/author/get-all-authors/**",
            "/api/v1/get-author-by-id/**",
            "/api/v1/event/get-all-events/**",
            "/api/v1/publisher/get-all-publishers/**",
            "/api/v1/author-management/get-books-by-author/**"
    };
    public static final String[] AUTHENTICATED = {
            "/test/testAuth",
            "/api/v1/auth/logout/**",
            "/api/v1/reservation/get-user-reservations/**",
            "/api/v1/reservation/get-reservation-details/**",
    };
    public static final String[] HAS_ROLE_SUPER_ADMIN = {
            "/api/v1/user/**",//test olunub ancaq todo:demek userupdate kecir authentication qoy
            "/api/v1/reservation/get-book-reservations/**",
            "/api/v1/reservation/cancel-reservation/**",
            "/api/v1/rating-and-review/delete-rating-and-review/**",
            "/api/v1/rating-and-review/get-ratings-and-reviews-book/**",
            "/api/v1/rating-and-review/get-ratings-and-reviews-user/**",
            "/api/v1/rating-and-review/get-average-rating/**",
            "/api/v1/report/**",
            "/api/v1/book/delete/**",//test edildi
            "/api/v1/author/delete/**",//test
            "/api/v1/publisher/delete/**",//test
            "/api/v1/event/delete-event/**",//test
            "/api/v1/reminder/delete-reminder/**",
            "/api/v1/notification/remove-notification/**",
            "/api/v1/book-management/get-book-inventory/**",//test
            "/api/v1/author-management/**"//test
    };
    public static final String[] HAS_ANY_ROLE_SUPER_ADMIN_AND_ADMIN = {
            "/api/v1/reservation/check-availability/**",
            "/api/v1/rentals/**",
            "/api/v1/book/**",
            "/api/v1/author/**",
            "/api/v1/publisher/**",
            "/api/v1/event/**",
            "/api/v1/reminder/**",
            "/api/v1/notification/**",
            "/api/v1/management/**",
    };
    public static final String[] HAS_ROLE_USER = {
            "/api/v1/reservation/add-reservation/**",
            "/api/v1/reservation/update-reservation/**",
            "/api/v1/rating-and-review/add-rating-and-review/**",
            "/api/v1/rating-and-review/update-rating-and-review/**",
            "/api/v1/notification/get-reminders-by-user/**",
            "/api/v1/notification/get-notifications-by-user/**"
    };
}
