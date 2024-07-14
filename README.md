# org.dynapi.openapispec
OpenAPI Specification Builder Library

```java
public class Example {
    public static void main(String[] args) {
        OpenApiInfo info = OpenApiInfo.builder()
                .title("MyAPI")
                .description("My API")
                .version("1.0.0")
                .logo("/assets/logo.png")
                .build();
        OpenApiSpec spec = new OpenApiSpec(info);
        spec.addPath(new Path("/hello/<name>")
                .addMethod("GET", new PathSchema()
                        .addPathParameter(Parameter.builder()
                                .name("name")
                                .description("Person to greet")
                                .schema(new TString())
                                .build()
                        )
                        .addResponse(200, new TString()
                                .example("Hello World")
                        )
                        .addResponse(500, new TObject()
                                .addProperty("error", new TString()
                                        .example("NullPointerException")
                                        .description("Error Group")
                                )
                                .addProperty("detail", new TString()
                                        .description("Detailed information about the error")
                                )
                        )
                )
        );
        JSONObject specification = spec.build();
        System.out.println(specification.toString(2));
    }
}
```
