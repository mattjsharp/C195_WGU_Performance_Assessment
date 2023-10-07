package C195.l10n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Contains a resource bundle to provide localization utilities.
 * @author mattjsharp
 */
public interface L10n {
    ResourceBundle l10n = ResourceBundle.getBundle("C195/l10n/login", Locale.getDefault());
}
