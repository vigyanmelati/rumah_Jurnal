package id.vigyan.rumahjurnal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DaftarJurnalActivityTest {
    @Rule
    public ActivityTestRule<DaftarJurnalActivity> mListDataRegistrasiTestRule =
            new ActivityTestRule<DaftarJurnalActivity>(DaftarJurnalActivity.class);

    @Test
    public void checkRecyclerView() throws Exception {
        onView(withId(R.id.jurnal_list))
                .check(matches(isDisplayed()));
    }
}
