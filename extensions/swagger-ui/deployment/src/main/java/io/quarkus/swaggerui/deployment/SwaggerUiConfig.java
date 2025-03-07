package io.quarkus.swaggerui.deployment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

import io.quarkus.runtime.annotations.ConfigDocMapKey;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.openapi.ui.DocExpansion;
import io.smallrye.openapi.ui.HttpMethod;
import io.smallrye.openapi.ui.ThemeHref;

@ConfigRoot
@ConfigMapping(prefix = "quarkus.swagger-ui")
public interface SwaggerUiConfig {

    /**
     * The path where Swagger UI is available.
     * <p>
     * The value `/` is not allowed as it blocks the application from serving anything else.
     * By default, this value will be resolved as a path relative to `${quarkus.http.non-application-root-path}`.
     */
    @WithDefault("swagger-ui")
    String path();

    /**
     * If this should be included every time. By default, this is only included when the application is running
     * in dev mode.
     */
    @WithDefault("false")
    boolean alwaysInclude();

    /**
     * The urls that will be included as options. By default, the OpenAPI path will be used.
     * Here you can override that and supply multiple urls that will appear in the TopBar plugin.
     */
    @ConfigDocMapKey("name")
    Map<String, String> urls();

    /**
     * If urls option is used, this will be the name of the default selection.
     */
    Optional<String> urlsPrimaryName();

    /**
     * The html title for the page.
     */
    Optional<String> title();

    /**
     * Swagger UI theme to be used.
     */
    Optional<ThemeHref> theme();

    /**
     * A footer for the html page. Nothing by default.
     */
    Optional<String> footer();

    /**
     * If set to true, enables deep linking for tags and operations.
     */
    Optional<Boolean> deepLinking();

    /**
     * Controls the display of operationId in operations list. The default is false.
     */
    Optional<Boolean> displayOperationId();

    /**
     * The default expansion depth for models (set to -1 completely hide the models).
     */
    OptionalInt defaultModelsExpandDepth();

    /**
     * The default expansion depth for the model on the model-example section.
     */
    OptionalInt defaultModelExpandDepth();

    /**
     * Controls how the model is shown when the API is first rendered.
     */
    Optional<String> defaultModelRendering();

    /**
     * Controls the display of the request duration (in milliseconds) for "Try it out" requests.
     */
    Optional<Boolean> displayRequestDuration();

    /**
     * Controls the default expansion setting for the operations and tags.
     */
    Optional<DocExpansion> docExpansion();

    /**
     * If set, enables filtering. The top bar will show an edit box that you can use to filter the tagged operations that
     * are shown.
     * Can be Boolean to enable or disable, or a string, in which case filtering will be enabled using that string as the
     * filter expression.
     * Filtering is case-sensitive matching the filter expression anywhere inside the tag.
     */
    Optional<String> filter();

    /**
     * If set, limits the number of tagged operations displayed to at most this many. The default is to show all operations.
     */
    OptionalInt maxDisplayedTags();

    /**
     * Apply a sort to the operation list of each API.
     * It can be 'alpha' (sort by paths alphanumerically), 'method' (sort by HTTP method) or a function (see
     * Array.prototype.sort() to know how sort function works).
     * Default is the order returned by the server unchanged.
     */
    Optional<String> operationsSorter();

    /**
     * Controls the display of vendor extension (x-) fields and values for Operations, Parameters, and Schema.
     */
    Optional<Boolean> showExtensions();

    /**
     * Controls the display of extensions (pattern, maxLength, minLength, maximum, minimum) fields and values for
     * Parameters.
     */
    Optional<Boolean> showCommonExtensions();

    /**
     * Apply a sort to the tag list of each API.
     * It can be 'alpha' (sort by paths alphanumerically) or a function (see Array.prototype.sort() to learn how to write a
     * sort function).
     * Two tag name strings are passed to the sorter for each pass. Default is the order determined by Swagger UI.
     */
    Optional<String> tagsSorter();

    /**
     * Provides a mechanism to be notified when Swagger UI has finished rendering a newly provided definition.
     */
    Optional<String> onComplete();

    /**
     * Set to {@code false} to deactivate syntax highlighting of payloads and cURL command. Can be otherwise an object with the
     * {@code activate} and {@code theme} properties.
     */
    Optional<String> syntaxHighlight();

    /**
     * OAuth redirect URL.
     */
    Optional<String> oauth2RedirectUrl();

    /**
     * MUST be a function. Function to intercept remote definition, "Try it out", and OAuth 2.0 requests.
     * Accepts one argument requestInterceptor(request) and must return the modified request, or a Promise that resolves to
     * the modified request.
     */
    Optional<String> requestInterceptor();

    /**
     * If set, MUST be an array of command line options available to the curl command.
     * This can be set on the mutated request in the requestInterceptor function.
     */
    Optional<List<String>> requestCurlOptions();

    /**
     * MUST be a function. Function to intercept remote definition, "Try it out", and OAuth 2.0 responses.
     * Accepts one argument responseInterceptor(response) and must return the modified response, or a Promise that resolves
     * to the modified response.
     */
    Optional<String> responseInterceptor();

    /**
     * If set to true, uses the mutated request returned from a requestInterceptor to produce the curl command in the UI,
     * otherwise the request before the requestInterceptor was applied is used.
     */
    Optional<Boolean> showMutatedRequest();

    /**
     * List of HTTP methods that have the "Try it out" feature enabled.
     * An empty array disables "Try it out" for all operations. This does not filter the operations from the display.
     */
    Optional<List<HttpMethod>> supportedSubmitMethods();

    /**
     * By default, Swagger UI attempts to validate specs against swagger.io's online validator.
     * You can use this parameter to set a different validator URL, for example for locally deployed validators (Validator
     * Badge).
     * Setting it to either none, 127.0.0.1 or localhost will disable validation.
     */
    Optional<String> validatorUrl();

    /**
     * If set to true, enables passing credentials, as defined in the Fetch standard, in CORS requests that are sent by the
     * browser.
     */
    Optional<Boolean> withCredentials();

    /**
     * Function to set default values to each property in model. Accepts one argument modelPropertyMacro(property), property
     * is immutable
     */
    Optional<String> modelPropertyMacro();

    /**
     * Function to set default value to parameters. Accepts two arguments parameterMacro(operation, parameter).
     * Operation and parameter are objects passed for context, both remain immutable
     */
    Optional<String> parameterMacro();

    /**
     * If set to true, it persists authorization data and it would not be lost on browser close/refresh
     */
    Optional<Boolean> persistAuthorization();

    /**
     * The name of a component available via the plugin system to use as the top-level layout for Swagger UI.
     */
    Optional<String> layout();

    /**
     * A list of plugin functions to use in Swagger UI.
     */
    Optional<List<String>> plugins();

    /**
     * A list of external scripts (usually plugins) to use in Swagger UI.
     */
    Optional<List<String>> scripts();

    /**
     * A list of presets to use in Swagger UI.
     */
    Optional<List<String>> presets();

    /**
     * OAuth default clientId - Used in the initOAuth method.
     */
    Optional<String> oauthClientId();

    /**
     * OAuth default clientSecret - Used in the initOAuth method.
     */
    Optional<String> oauthClientSecret();

    /**
     * OAuth1 Realm query parameter added to authorizationUrl and tokenUrl - Used in the initOAuth method.
     */
    Optional<String> oauthRealm();

    /**
     * OAuth application name, displayed in authorization popup - Used in the initOAuth method.
     */
    Optional<String> oauthAppName();

    /**
     * OAuth scope separator for passing scopes - Used in the initOAuth method.
     */
    Optional<String> oauthScopeSeparator();

    /**
     * OAuth Scopes, separated using the oauthScopeSeparator - Used in the initOAuth method.
     */
    Optional<String> oauthScopes();

    /**
     * OAuth additional query parameters added to authorizationUrl and tokenUrl - Used in the initOAuth method.
     */
    Optional<String> oauthAdditionalQueryStringParams();

    /**
     * OAuth only activated for the accessCode flow. During the authorization_code request to the tokenUrl, pass the Client
     * Password using the HTTP Basic Authentication scheme - Used in the initOAuth method.
     */
    Optional<Boolean> oauthUseBasicAuthenticationWithAccessCodeGrant();

    /**
     * OAuth only applies to authorization code flows. Proof Key for Code Exchange brings enhanced security for OAuth public
     * clients - Used in the initOAuth method.
     */
    Optional<Boolean> oauthUsePkceWithAuthorizationCodeGrant();

    /**
     * Pre-authorize Basic Auth, programmatically set DefinitionKey for a Basic authorization scheme - Used in the
     * preauthorizeBasic method.
     */
    Optional<String> preauthorizeBasicAuthDefinitionKey();

    /**
     * Pre-authorize Basic Auth, programmatically set Username for a Basic authorization scheme - Used in the preauthorizeBasic
     * method.
     */
    Optional<String> preauthorizeBasicUsername();

    /**
     * Pre-authorize Basic Auth, programmatically set Password for a Basic authorization scheme - Used in the preauthorizeBasic
     * method.
     */
    Optional<String> preauthorizeBasicPassword();

    /**
     * Pre-authorize ApiKey Auth, programmatically set DefinitionKey for an API key or Bearer authorization scheme - Used in the
     * preauthorizeApiKey method.
     */
    Optional<String> preauthorizeApiKeyAuthDefinitionKey();

    /**
     * Pre-authorize ApiKey Auth, programmatically set ApiKeyValue for an API key or Bearer authorization scheme - Used in the
     * preauthorizeApiKey method.
     */
    Optional<String> preauthorizeApiKeyApiKeyValue();

    /**
     * If set to true, this allows the user to modify and test different query parameters in the API request
     */
    @WithDefault("false")
    boolean queryConfigEnabled();

    /**
     * If try it out should be enabled by default
     */
    @WithDefault("false")
    boolean tryItOutEnabled();
}
