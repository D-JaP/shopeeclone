// unit test
import { convertStringFromSlugExcludeEndId } from "./utils";

test('Slug to string without id', () => {
    expect(convertStringFromSlugExcludeEndId("smartphones-17")).toBe("Smartphones")
})
