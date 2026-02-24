import { test, expect } from '@playwright/test';

test.describe('EPAM - Client Work', () => {
test('should navigate to Client Work via Services and show Client Work text', async ({ page }) => {
  // Navigate to EPAM homepage
  await page.goto('https://www.epam.com/', { waitUntil: 'networkidle' });

  // Dismiss cookie/privacy banners if present
  const acceptButtons = page.getByRole('button', { name: /accept|agree|got it|I agree/i });
  if (await acceptButtons.count() > 0) {
    await acceptButtons.first().click().catch(() => {});
  }

  // Click Services in header
  const services = page.getByRole('link', { name: /Services/i }).first();
  await services.scrollIntoViewIfNeeded();
  await services.click({ timeout: 10000 }).catch(async () => {
    // fallback: click via JS if blocked
    await page.evaluate(() => {
      const link = Array.from(document.querySelectorAll('a')).find(a => /\bServices\b/i.test(a.textContent || ''));
      if (link) (link as HTMLAnchorElement).click();
    });
  });

  // Click Explore Our Client Work
  const explore = page.getByRole('link', { name: /Explore Our Client Work/i }).first();
  await explore.scrollIntoViewIfNeeded();
  await explore.click({ timeout: 10000 }).catch(async () => {
    await page.evaluate(() => {
      const link = Array.from(document.querySelectorAll('a')).find(a => /Explore Our Client Work/i.test(a.textContent || '')) || Array.from(document.querySelectorAll('a')).find(a => /Client Work/i.test(a.textContent || ''));
      if (link) (link as HTMLAnchorElement).click();
    });
  });

  await page.waitForLoadState('networkidle');

  // Verify Client Work text is visible
  await expect(page.getByText(/Client Work/i).first()).toBeVisible();
});
});
